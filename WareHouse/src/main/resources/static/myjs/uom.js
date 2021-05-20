 $(document).ready(function () {

            //1. hide error section
            $("#uomTypeError").hide();
            $("#uomModelError").hide();
            $("#uomDescError").hide();

            // 2. difine error variable
            var uomTypeError = false;
            var uomModelError = false;
            var uomDescError = false;

            //3. difine funtion 
            function validate_uomType() {
                var val = $("#uomType").val();

                if (val == '') {
                    $("#uomTypeError").show();
                    $("#uomTypeError").html(" * Please select <b>Uom type</b> ");
                    $("#uomTypeError").css('color', 'red');
                    uomTypeError = false;

                }
                else {
                    $("#uomTypeError").hide();
                    uomTypeError = true;
                }
                return uomTypeError;
            }

            function validate_uomModel() {
                var val = $("#uomModel").val();
                var exp = /^[A-Z\-\s]{4,12}$/;
                if (val == '') {
                    $("#uomModelError").show();
                    $("#uomModelError").html(" * Please enter  <b>Model</b> ");
                    $("#uomModelError").css('color', 'red');
                } else if (!exp.test(val)) {
                    $("#uomModelError").show();
                    $("#uomModelError").html("Only 4-12 chars allowed");
                    $("#uomModelError").css('color', 'red');
                    uomModelError = false;
                }
          
                  else {
			var id = 0 ; //for register
			if($("#id").val()!=undefined) { //for edit
				id = $("#id").val(); //if present
			}
        	//ajax call start    
        	$.ajax({
				url : 'validate',
				data: { "model": val,"id":id},
				success:function(resTxt) {
					if(resTxt!='') { //error, duplicate exist
						$("#uomModelError").show();
            			$("#uomModelError").html(resTxt);
             			$("#uomModelError").css('color', 'red');
             		    uomModelError = false;
					} else { //valid, no duplicate
						$("#uomModelError").hide();
             		    uomModelError = true;
					}
				}
			});
        	//ajax call end    
        }



                return uomModelError;
            }

            function validate_uomDesc() {
                var val = $("#uomDesc").val();
                var exp = /^[A-Za-z0-9\-\s\.\:\@\,]{10,100}$/;

                if (val == '') {
                    $("#uomDescError").show();
                    $("#uomDescError").html(" * Please Enter <b> Description </b> ");
                    $("#uomDescError").css('color', 'red');

                }else if (!exp.test(val)) {
                    $("#uomDescError").show();
                    $("#uomDescError").html("Only 10-100 chars allowed");
                    $("#uomDescError").css('color','red');
                    uomDescError = false;
                }
                else {
                    $("#uomDescError").hide();
                    uomDescError = true;
                }
                return uomDescError;
            }




            //4. link with action event 
            $("#uomType").change(function () {

                validate_uomType();

            });

            $("#uomModel").keyup(function () {
                //to uppercase
         $(this).val($(this).val().toUpperCase());
                validate_uomModel();
            });

            $("#uomDesc").keyup(function () {
                validate_uomDesc();
            });


            // 5. on click submit
            
            $("#UomRegister").submit(function(){
                 
              validate_uomType();
              validate_uomModel();
              validate_uomDesc();
              

              if(uomTypeError && uomModelError && uomDescError)
                   return true;
               else return false;

            });
            
            

        });