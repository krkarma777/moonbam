ClassicEditor 

    .create( document.querySelector( '#txtContent' ), {

        language: "ko",
        ckfinder : {

            uploadUrl: "/acorn/image/upload",
            withCredentials: true
        }
    } )