$(document).ready(function () {

            // 1. hide Error section ----------
            $("#partCodeError").hide();
            $("#partDimsError").hide();
            $("#partBaseCostError").hide();
            $("#partCurrencyError").hide();
            $("#uomError").hide();
            $("#omError").hide();
            $("#partDescError").hide();

            // difine error variable

            var partCodeError = false;
            var partDimsError = false;
            var partBaseCostError = false;
            var partCurrencyError = false;
            var uomError = false;
            var partDescError = false;
             var   omError =false;


            // difine funtion validation

            function validate_partCode() {
                var val = $("#partCode").val();
                var exp = /^[A-Z\-\s]{4,8}$/;

                if (val == '') {
                    $("#partCodeError").show();
                    $("#partCodeError").html(" * Code Can't  <b> Empty </b>");
                    $("#partCodeError").css('color', 'red');


                }
                else if (!exp.test(val)) {
                    $("#partCodeError").show();
                    $("#partCodeError").html("*<b>Code</b> must be 4-8 uppercase letters");
                    $("#partCodeError").css('color', 'red');
                    partCodeError = false;
                }


                else {
                    var id = 0; //for register
                    if ($("#id").val() != undefined) { //for edit
                        id = $("#id").val(); //if present
                    }
                    //ajax call start    
                    $.ajax({
                        url: 'validate',
                        data: { "code": val, "id": id },
                        success: function (resTxt) {
                            if (resTxt != '') { //error, duplicate exist
                                $("#partCodeError").show();
                                $("#partCodeError").html(resTxt);
                                $("#partCodeError").css('color', 'red');
                                userCodeError = false;
                            } else { //valid, no duplicate
                                $("#partCodeError").hide();
                                partCodeError = true;
                            }
                        }
                    });
                    //ajax call end    
                }

                return partCodeError;
            }


            function validate_partDimens() {

                var val = $("#partWid").val();
                var val1 = $("#partLen").val();
                var val2 = $("#partHt").val();

                var exp = /^[0-9\.0]{1,6}$/;

                if (val == '' || val1 == '' || val2 == '') {
                    $("#partDimsError").show();
                    $("#partDimsError").html(" * Dimensions can't Empty ");
                    $("#partDimsError").css('color', 'red');
                }

                else if (val <= 0 || val1 <= 0 || val2 <= 0) {
                    $("#partDimsError").show();
                    $("#partDimsError").html(" * Dimensions must be > 0 ");
                    $("#partDimsError").css('color', 'red');
                }

                else if (!exp.test(val) || !exp.test(val1) || !exp.test(val2)) {
                    $("#partDimsError").show();
                    $("#partDimsError").html("*<b> In valid Dimensions</b");
                    $("#partDimsError").css('color', 'red');
                    partDimsError = false;
                }

                else {
                    $("#partDimsError").hide();
                    partDimsError = true;
                }

                return partDimsError;
            }

            function validate_partBaseCost() {

                var val = $("#partBaseCost").val();


                var exp = /^[0-9\.]{1,8}$/;

                if (val == '') {
                    $("#partBaseCostError").show();
                    $("#partBaseCostError").html(" * Cost  can't Empty ");
                    $("#partBaseCostError").css('color', 'red');
                }

                else if (val <= 0) {
                    $("#partBaseCostError").show();
                    $("#partBaseCostError").html(" * Cost must be > 0 ");
                    $("#partBaseCostError").css('color', 'red');
                }

                else if (!exp.test(val)) {
                    $("#partBaseCostError").show();
                    $("#partBaseCostError").html("*<b> In valid Cost</b");
                    $("#partBaseCostError").css('color', 'red');
                    partBaseCostError = false;
                }

                else {
                    $("#partBaseCostError").hide();
                    partBaseCostError = true;
                }

                return partBaseCostError;
            }

            function validate_partCurrency() {

                var val = $("#partCurrency").val();
                if (val == '') {
                    $("#partCurrencyError").show();
                    $("#partCurrencyError").html(" * Please Select one <b> Currency</b> ");
                    $("#partCurrencyError").css('color', 'red');
                    userIdTypeError = false;

                }
                else {
                    $("#partCurrencyError").hide();
                    partCurrencyError = true;
                }

                return partCurrencyError;
            }

            function validate_uomId() {

               var val = $("#uomId").val();
                
                if (val=='') {
                    $("#uomError").show();
                    $("#uomError").html(" * Please Select one <b> UOM</b> ");
                    $("#uomError").css('color', 'red');
                    uomError = false;

                }
                else {
                    $("#uomError").hide();
                    uomError = true;
                }

                return uomError;
            }
            
            function validate_omId() {

               var val = $("#omId").val();
                
                if (val=='') {
                    $("#omError").show();
                    $("#omError").html(" * Please Select one <b>Order Method</b> ");
                    $("#omError").css('color', 'red');
                    omError = false;

                }
                else {
                    $("#omError").hide();
                    omError = true;
                }

                return omError;
            }

            function validate_partDesc() {
                var val = $("#partDesc").val();
                var exp = /^[A-Za-z0-9\-\s\.\:\@\,]{10,100}$/;

                if (val == '') {
                    $("#partDescError").show();
                    $("#partDescError").html(" * Please Enter <b> Description </b> ");
                    $("#partDescError").css('color', 'red');

                } else if (!exp.test(val)) {
                    $("#partDescError").show();
                    $("#partDescError").html("Only 10-100 chars allowed");
                    $("#partDescError").css('color', 'red');
                    partDescError = false;
                }
                else {
                    $("#partDescError").hide();
                    partDescError = true;
                }
                return partDescError;
            }








            // link with action event 
            $("#partCode").keyup(function () {
                $(this).val($(this).val().toUpperCase());
                validate_partCode();
            });

            $("#partWid").keyup(function () {

                validate_partDimens();
            });
            $("#partLen").keyup(function () {

                validate_partDimens();
            });
            $("#partHt").keyup(function () {

                validate_partDimens();
            });

            $("#partBaseCost").keyup(function () {

                validate_partBaseCost();
            });

            $("#partCurrency").change(function () {

                validate_partCurrency();
            });

            $("#uomId").change(function () {

                validate_uomId();
            });
            
             $("#omId").change(function () {

                validate_omId();
            });

            $("#partDesc").keyup(function () {

                validate_partDesc();
            });






            // 5.click on submit   
            $("#myPartForm").submit(function () {

                validate_partCode();
                validate_partDimens();
                validate_partBaseCost();
                validate_partCurrency();
                validate_uomId();
                validate_omId();
                validate_partDesc();





                if (partCodeError && partDimsError && partBaseCostError && partCurrencyError && uomError && omError&& partDescError)
                    return true;
                else return false;

            });






        });