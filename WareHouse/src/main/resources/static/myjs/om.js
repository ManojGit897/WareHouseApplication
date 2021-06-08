 $(document).ready(function(){
    
          // 1. hide Error section ----------
          $("#orderModeError").hide();
          $("#orderCodeError").hide();
          $("#orderTypeError").hide();
          $("#orderAcptError").hide();
          $("#orderDescError").hide();
    
          // difine error variable
           
          var orderModeError=false;
          var orderCodeError=false;
          var orderTypeError=false;
          var orderAcptError=false;
          var orderDescError=false;
    
          // difine funtion valide
          //.1..............................
          function validate_orderMode(){
             var val=$('[name="orderMode"]:checked').length;
             if(val==''){
                $("#orderModeError").show();
                $("#orderModeError").html(" * Please select one  <b> Order Mode </b>");
                $("#orderModeError").css('color','red');
                orderModeError=false;
                
            }else{
                $("#orderModeError").hide();
                orderModeError=true;
            }
    
    
             return orderModeError;
                }
              //2.................................
              function validate_orderCode(){
        var val=$("#orderCode").val();
            var exp = /^[A-Z\-\s]{4,8}$/;
        
        if(val==''){
            $("#orderCodeError").show();
            $("#orderCodeError").html(" * Please select <b> code </b>");
            $("#orderCodeError").css('color','red');
        }
        else if (!exp.test(val)) {
                    $("#orderCodeError").show();
                    $("#orderCodeError").html("*<b>Code</b> must be 4-8 uppercase letters");
                    $("#orderCodeError").css('color', 'red');
                    orderCodeError = false;
                }
        
        
        else {
			var id = 0 ; //for register
			if($("#id").val()!=undefined) { //for edit
				id = $("#id").val(); //if present
			}
        	//ajax call start    
        	$.ajax({
				url : 'validate',
				data: { "code": val,"id":id},
				success:function(resTxt) {
					if(resTxt!='') { //error, duplicate exist
						$("#orderCodeError").show();
            			$("#orderCodeError").html(resTxt);
             			$("#orderCodeError").css('color', 'red');
             		    orderCodeError = false;
					} else { //valid, no duplicate
						$("#orderCodeError").hide();
                        orderCodeError = true;
					}
				}
			});
        	//ajax call end    
        }

        return orderCodeError;
            }
    
         //3.............................

          function validate_orderType(){
    
            var val=$("#orderType").val();
            if(val==''){
                $("#orderTypeError").show();
                $("#orderTypeError").html(" * Please select one <b>Order Type</b> ");
                $("#orderTypeError").css('color','red');
                orderTypeError=false;
                
            }else{
                $("#orderTypeError").hide();
                orderTypeError=true;
            }
    
            return orderTypeError;
          }
    
          
          //4................................

          function validate_orderAcpt(){
              
            var val=$('[name="orderAcpt"]:checked').length;
             if(val==''){
                $("#orderAcptError").show();
                $("#orderAcptError").html(" * Please select one  <b> Order Accept</b>");
                $("#orderAcptError").css('color','red');
                orderAcptError=false;
                
            }else{
                $("#orderAcptError").hide();
                orderAcptError=true;
            }
    
    
             return orderAcptError;
            }
            //5...........
            function validate_orderDesc() {
                var val = $("#orderDesc").val();
                var exp = /^[A-Za-z0-9\-\s\.\:\@\,]{10,100}$/;

                if (val == '') {
                    $("#orderDescError").show();
                    $("#orderDescError").html(" * Please Enter <b> Description </b> ");
                    $("#orderDescError").css('color', 'red');
             

                }
                else if (!exp.test(val)) {
                    $("#orderDescError").show();
                    $("#orderDescError").html("Only 10-100 chars allowed");
                    $("#orderDescError").css('color','red');
                    orderDescError = false;
                    }
                else {
                    $("#orderDescError").hide();
                    orderDescError = true;
                }
                return orderDescError;
            }

         // 4. link with Action Event 
           
         $('[name="orderMode"]').change(function(){
               validate_orderMode();
         });

         $("#orderCode").keyup(function(){
            $(this).val($(this).val().toUpperCase());
               validate_orderCode();
         });
    
         $("#orderType").change(function(){
               validate_orderType();
         });
    
    
         $('[name="orderAcpt"]').change(function(){
               validate_orderAcpt();
         });
    
         $("#orderDesc").keyup(function(){
               validate_orderDesc();
         });
    
        $("#myOmForm").submit(function(){
                 validate_orderMode();
                 validate_orderCode();
                 validate_orderAcpt();
                  validate_orderDesc();
                   validate_orderType();
                 
                
             if(orderModeError&&orderCodeError&&orderAcptError&&orderDescError&&orderTypeError)
              

            
                   return true;
               else return false;

            });
            
    
    
    
        });