$(document).ready(function(){

      // 1. hide Error section ----------
      $("#shipModeError").hide();
      $("#shipCodeError").hide();
      $("#enableShipError").hide();
      $("#shipGradeError").hide();
      $("#shipDescError").hide();

      // difine error variable
       
      var shipModeError=false;
      var shipCodeError=false;
      var enableShipError=false;
      var shipGradeError=false;
      var shipDescError=false;

      // difine funtion valide
      function validate_shipMode(){

        var val=$("#shipMode").val();
        if(val==''){
            $("#shipModeError").show();
            $("#shipModeError").html(" * Please select <b>Mode</b> ");
            $("#shipModeError").css('color','red');
            shipModeError=false;
            
        }else{
            $("#shipModeError").hide();
            shipModeError=true;
        }

        return shipModeError;
      }

      function validate_shipCode(){
        var val=$("#shipCode").val();
            var exp = /^[A-Z\-\s]{4,8}$/;
        
        if(val==''){
            $("#shipCodeError").show();
            $("#shipCodeError").html(" * Please select <b> code </b>");
            $("#shipCodeError").css('color','red');
            shipCodeError=false;
            
        }
        else if (!exp.test(val)) {
                    $("#shipCodeError").show();
                    $("#shipCodeError").html("*<b>Code</b> must be 2-4 uppercase letters");
                    $("#shipCodeError").css('color', 'red');
                    shipCodeError = false;
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
						$("#shipCodeError").show();
            			$("#shipCodeError").html(resTxt);
             			$("#shipCodeError").css('color', 'red');
             		    shipCodeError = false;
					} else { //valid, no duplicate
						$("#shipCodeError").hide();
             		    shipCodeError = true;
					}
				}
			});
        	//ajax call end    
        }

        return shipCodeError;
            }

     function validate_enableShip(){
         var val=$('[name="enableShip"]:checked').length;
         if(val==''){
            $("#enableShipError").show();
            $("#enableShipError").html(" * Please select one  <b> Shipment </b>");
            $("#enableShipError").css('color','red');
            enableShipError=false;
            
        }else{
            $("#enableShipError").hide();
            enableShipError=true;
        }


         return enableShipError;
            }

      function validate_shipGrade(){
          
        var val=$('[name="shipGrade"]:checked').length;
         if(val==''){
            $("#shipGradeError").show();
            $("#shipGradeError").html(" * Please select one  <b> Shipment  Grade</b>");
            $("#shipGradeError").css('color','red');
            enableShipError=false;
            
        }else{
            $("#shipGradeError").hide();
            shipGradeError=true;
        }


         return shipGradeError;
        }
    function validate_shipDesc(){
      
        var val=$("#shipDesc").val();
        if(val==''){
            $("#shipDescError").show();
            $("#shipDescError").html(" * Please Enter <b> Description</b> ");
            $("#shipDescError").css('color','red');
            shipDescError=false;
            
        }else{
            $("#shipDescError").hide();
            shipDescError=true;
        }


      return shipDescError;
     }

     // 4. link with Action Event 
     $("#shipMode").change(function(){
           validate_shipMode();
     });

     $("#shipCode").keyup(function(){
     $(this).val($(this).val().toUpperCase());
           validate_shipCode();
     });

     $('[name="enableShip"]').change(function(){
           validate_enableShip();
     });

     $('[name="shipGrade"]').change(function(){
           validate_shipGrade();
     });

     $("#shipDesc").keyup(function(){
           validate_shipDesc();
     });

     // 5.click on submit
     $("#shipmentTypeRegister").submit(function(){
          
       validate_shipMode();
       validate_shipCode();
       validate_enableShip();
       validate_shipGrade();
       validate_shipDesc();

       if(shipModeError && shipCodeError && enableShipError
            && shipGradeError && shipDescError)
            return true;
        else return false;

     });




    });