const urlAPI="/bin/servlet/api"

                $.ajax({
                    url: urlAPI,
                    type: "GET",
                    success: (response) => {
                        console.log(response)
                    },
                    error: (xhr, status, error) => {
                        console.log(error);
                    },
                });