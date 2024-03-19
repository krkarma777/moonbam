ClassicEditor 

    .create( document.querySelector( '#txtContent' ) ) 

    .then( editor => { 

        console.log( editor ); 

    } ) 

    .catch( error => { 

        console.error( error ); 

    } );

