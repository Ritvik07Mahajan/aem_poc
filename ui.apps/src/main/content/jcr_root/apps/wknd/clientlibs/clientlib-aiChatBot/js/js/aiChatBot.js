

document.addEventListener('DOMContentLoaded', () => {
    const chatMessages = document.getElementById('chatMessages');
    const userInput = document.getElementById('userInput');
    const sendBtn = document.getElementById('sendBtn');

    const OPENROUTER_API_KEY = "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"; // DO NOT EXPOSE IN PRODUCTION!

    let conversationHistory = []; // To maintain context

    function appendMessage(sender, text) {
        const messageDiv = document.createElement('div');
        messageDiv.classList.add('message', sender);

        if (text) {
            const textContent = document.createElement('p');
            textContent.textContent = text;
            messageDiv.appendChild(textContent);
        }

        chatMessages.appendChild(messageDiv);
    }

    async function sendMessage() {
        const userMessage = userInput.value.trim();
        if (userMessage === '') return;

        appendMessage('user', "User: "+userMessage);
        conversationHistory.push({ role: 'user', content: [{ type: 'text', text: userMessage }] });
        userInput.value = '';

       
        let messageContent = [{ type: 'text', text: userMessage }];

        try {
            const response = await fetch("https://openrouter.ai/api/v1/chat/completions", {
                method: "POST",
                headers: {
                    "Authorization": `Bearer ${OPENROUTER_API_KEY}`,
                    "Content-Type": "application/json"
                },
                body: JSON.stringify({
                    "model": "x-ai/grok-4-fast:free", // Or any other model that supports vision
                    "messages": conversationHistory.map(msg => {
                        return { role: msg.role, content: msg.content[0].text }; // Simplified for text-only history
                    }).concat([{ role: 'user', content: messageContent }]) // Add the current message
                })
            });

            const data = await response.json();

            if (data.choices && data.choices.length > 0 && data.choices[0].message) {
                const botResponse = data.choices[0].message.content;
                appendMessage('bot', botResponse);
                conversationHistory.push({ role: 'assistant', content: [{ type: 'text', text: botResponse }] }); // Add bot response to history
            } else {
                appendMessage('bot', 'Sorry, I could not get a response from the AI.');
            }

        } catch (error) {
            console.error("Error communicating with OpenRouter API:", error);
            appendMessage('bot', 'An error occurred while connecting to the AI. Please try again.');
        }
    }

    sendBtn.addEventListener('click', sendMessage);

    // Initial greeting
    appendMessage('bot', 'Hello! How can I help you today?');
});