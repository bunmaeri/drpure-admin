$(function() {
    $('.summernote')
      .froalaEditor({
    	enter: $.FroalaEditor.ENTER_BR,
  		theme: 'royal',
  		language: 'ko',
//  		fontFamily: {       // https://www.froala.com/wysiwyg-editor/docs/examples/font-family
//  	      "맑은 고딕": '맑은 고딕',
//  	      "굴림": '굴림',
//  	      "Malgun Gothic": 'Malgun Gothic',
//  	      "gulim": 'gulim'
//  	    },
  		inlineStyles: {
  	        'Red': 'color:#ce0000;',
  	        'Blue': 'color:#3984c6;'
  	    },
//	  	toolbarButtons :  ['fullscreen', 'bold', 'italic', 'underline', 'strikeThrough', 'subscript', 'superscript', 'fontFamily', 'fontSize', '|', 'color', 'emoticons', 'inlineStyle', 'paragraphStyle', '|', 'paragraphFormat', 'align', 'formatOL', 'formatUL', 'outdent', 'indent', 'quote', 'insertHR', 'insertLink', 'insertImage', 'insertVideo', 'insertTable', 'undo', 'redo', 'clearFormatting', 'selectAll', 'html'],
//	    toolbarButtonsMD: ['fullscreen', 'bold', 'italic', 'underline', 'strikeThrough', 'subscript', 'superscript', 'fontFamily', 'fontSize', '|', 'color', 'emoticons', 'inlineStyle', 'paragraphStyle', '|', 'paragraphFormat', 'align', 'formatOL', 'formatUL', 'outdent', 'indent', 'quote', 'insertHR', 'insertLink', 'insertImage', 'insertVideo', 'insertTable', 'undo', 'redo', 'clearFormatting', 'selectAll', 'html'],
//	    toolbarButtonsSM: ['fullscreen', 'bold', 'italic', 'underline', 'fontFamily', 'fontSize', 'color', 'emoticons', 'insertLink', 'insertImage', 'insertVideo', 'insertTable', 'clearFormatting', 'undo', 'redo'],
//	    toolbarButtonsXS: ['bold', 'italic', 'fontFamily', 'fontSize', 'insertLink', 'insertImage', 'insertVideo','clearFormatting', 'undo', 'redo'],
    
        // Set the image upload parameter.
        //imageUploadParam: 'image_param',

        // Set the image upload URL.
        imageUploadURL: 'index.php?route=common/imagemanager/upload&token=' + getURLVar('token'),
        fileUploadURL: 'index.php?route=common/imagemanager/upload&token=' + getURLVar('token'),
        videoUploadURL: 'index.php?route=common/imagemanager/upload&token=' + getURLVar('token'),
        imageManagerLoadURL: 'index.php?route=common/imagemanager&token=' + getURLVar('token'),
//        imageManagerDeleteURL: 'index.php?route=common/imagemanager/delete&token=' + getURLVar('token'),
//        imageManagerDeleteParams: {},
        imageManagerPageSize: 12,
        imageManagerScrollOffset: 10,

        // Additional upload params.
        //imageUploadParams: {id: 'my_editor'},

        // Set request type.
        imageUploadMethod: 'POST',

        // Set max image size to 5MB.
        imageMaxSize: 50 * 1024 * 1024,

        // Allow to upload PNG and JPG.
        imageAllowedTypes: ['jpeg', 'jpg', 'png']
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