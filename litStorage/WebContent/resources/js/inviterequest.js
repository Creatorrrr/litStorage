 $( function() {
    var dialog, form;
 

 
    
 
    function addUser() {
      var valid = true;
//      allFields.removeClass( "ui-state-error" );
 
      if ( valid ) {
        dialog.dialog( "close" );
      }
      return valid;
    }
 
    dialog = $( "#dialog-form" ).dialog({
      autoOpen: false,
      height: 400,
      width: 350,
      modal: true,
      buttons: {
        "요청 메시지 전송": addUser,
        Cancel: function() {
          dialog.dialog( "close" );
        }
      },
      close: function() {
        form[ 0 ].reset();
//        allFields.removeClass( "ui-state-error" );
      }
    });
 
    form = dialog.find( "form" ).on( "submit", function( event ) {
      event.preventDefault();
      addUser();
    });
 
    $( "#sendRequest" ).button().on( "click", function() {
      dialog.dialog( "open" );
    });
  } );