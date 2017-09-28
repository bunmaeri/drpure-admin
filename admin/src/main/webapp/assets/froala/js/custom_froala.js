$(function(){
    $('.summernote').froalaEditor({
    	enter: $.FroalaEditor.ENTER_P,
    	placeholderText: null,
    	inlineStyles: {
  	        'Red': 'color:#ce0000;',
  	        'Blue': 'color:#3984c6;'
  	    },
  	    fontFamily: {
  	    	'Verdana,Geneva,sans-serif': 'Verdana',  	    
  	    	'Georgia,serif': 'Georgia',
  	    	'Arial,Helvetica,sans-serif': 'Arial',
  	        'Impact,Charcoal,sans-serif': 'Impact',
  	        'Tahoma,Geneva,sans-serif': 'Tahoma',
  	        "'Times New Roman',Times,serif": 'Times New Roman'
  	    },
        fontFamilySelection: true,
        // Define new inline styles.
        inlineStyles: {
  	        'Red': 'color:#ce0000;',
  	        'Blue': 'color:#3984c6;'
  	    },
  	    // Set the image upload URL.
        imageUploadURL: '/imageUpload.dr',
     // Set the image manager URL.
        imageManagerLoadURL: '/imageManager.dr',
        // Set the image delete URL.
        imageManagerDeleteURL: '/deleteImage.dr',

        imageManagerPageSize: 20,
        imageManagerScrollOffset: 10,

        // Set request type.
        imageUploadMethod: 'POST',

        // Set max image size to 5MB.
        imageMaxSize: 50 * 1024 * 1024,

        // Allow to upload PNG and JPG.
        imageAllowedTypes: ['jpeg', 'jpg', 'png']
      
        //,toolbarButtons: ['fullscreen', 'bold', 'italic', 'underline', 'strikeThrough', 'subscript', 'superscript', 'fontFamily', 'fontSize', '|', 'color', 'emoticons', 'inlineStyle', 'paragraphStyle', '|', 'paragraphFormat', 'align', 'formatOL', 'formatUL', 'outdent', 'indent', '-', 'insertLink', 'insertImage', 'insertVideo', 'insertFile', 'insertTable', '|', 'quote', 'insertHR', 'undo', 'redo', 'clearFormatting', 'selectAll', 'html']
    })
    .on('froalaEditor.image.beforeUpload', function (e, editor, images) {
      // Return false if you want to stop the image upload.
    })
    .on('froalaEditor.image.uploaded', function (e, editor, response) {
      // Image was uploaded to the server.
    })
    .on('froalaEditor.image.inserted', function (e, editor, $img, response) {
      // Image was inserted in the editor.
    })
    .on('froalaEditor.image.replaced', function (e, editor, $img, response) {
      // Image was replaced in the editor.
    })
    .on('froalaEditor.image.error', function (e, editor, error, response) {
      // Bad link.
      if (error.code == 1) {
      	alert("Bad link.");
      }
      // No link in upload response.
      else if (error.code == 2) {
      	alert("No link in upload response.");
      }
      // Error during image upload.
      else if (error.code == 3) {
      	alert("Error during image upload.");
      }
      // Parsing response failed.
      else if (error.code == 4) {
      	alert("Parsing response failed");
      }
      // Image too text-large.
      else if (error.code == 5) {
      	alert("Image too text-large.");
      }
      // Invalid image type.
      else if (error.code == 6) {
      	alert("Invalid image type.");
      }
      // Image can be uploaded only to same domain in IE 8 and IE 9.
      else if (error.code == 7) {
      	alert("Image can be uploaded only to same domain in IE 8 and IE 9.");
      }
      // Response contains the original server response to the request if available.
    });
   
  });