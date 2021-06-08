$(document).ready(function () {

        // 1. hide Error section ------
        $("#orderCodeError").hide();
        $("#stIdError").hide();
        $("#customerIdError").hide();
        $("#refNumError").hide();
        $("#stockModeError").hide();
        $("#stockSourceError").hide();
        $("#descriptionError").hide();

        // difine error variable

        var orderCodeError = false;
        var stIdError = false;
        var customerIdError = false;
        var refNumError = false;
        var stockModeError = false;
        var stockSourceError = false;
        var descriptionError = false;

        // difine funtion valide
        function validate_orderCode() {
            var val = $("#orderCode").val();
            var exp = /^[A-Z\-\s]{4,12}$/;

            if (val == '') {
                $("#orderCodeError").show();
                $("#orderCodeError").html(" *  code can't<b> Empty</b>");
                $("#orderCodeError").css('color', 'red');


            }
            else if (!exp.test(val)) {
                $("#orderCodeError").show();
                $("#orderCodeError").html("*<b>Code</b> must be 4-12 uppercase letters");
                $("#orderCodeError").css('color', 'red');
                orderCodeError = false;
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

        function validate_stId() {

            var val = $("#stId").val();
            if (val == '') {
                $("#stIdError").show();
                $("#stIdError").html(" * Please Select one <b>Shipment code</b> ");
                $("#stIdError").css('color', 'red');
                stIdError = false;

            } else {
                $("#stIdError").hide();
                stIdError = true;
            }

            return stIdError;
        }

        function validate_customerId() {

            var val = $("#customerId").val();
            if (val == '') {
                $("#customerIdError").show();
                $("#customerIdError").html(" * Please Select one <b>Customer</b> ");
                $("#customerIdError").css('color', 'red');
                customerIdError = false;

            } else {
                $("#customerIdError").hide();
                customerIdError = true;
            }

            return customerIdError;
        }

        function validate_refNum() {

            var val = $("#refNum").val();
            //var exp = /^[0-9]{10}$/;

            if (val == '') {
                $("#refNumError").show();
                $("#refNumError").html(" * Ref Num can't be <b> Empty</b> ");
                $("#refNumError").css('color', 'red');
                refNumError = false;
            }
            else {
                $("#refNumError").hide();
                refNumError = true;
            }

            return refNumError;
        }

        function validate_stockMode() {
            var val = $('[name="stockMode"]:checked').length;
            if (val == '') {
                $("#stockModeError").show();
                $("#stockModeError").html(" * Please select one  <b> Stock Mode </b>");
                $("#stockModeError").css('color', 'red');
                stockModeError = false;

            } else {
                $("#stockModeError").hide();
                stockModeError = true;
            }


            return stockModeError;
        }
              
        function validate_stockSource() {

            var val = $("#stockSource").val();
            if (val == '') {
                $("#stockSourceError").show();
                $("#stockSourceError").html(" * Please Select one <b>Stock Source</b> ");
                $("#stockSourceError").css('color', 'red');
                stockSourceError = false;

            }
            else {
                $("#stockSourceError").hide();
                stockSourceError = true;
            }

            return stockSourceError;
        }


        function validate_description() {
            var val = $("#description").val();

            if (val == '') {
                $("#descriptionError").show();
                $("#descriptionError").html(" * Please Enter <b> Description </b> ");
                $("#descriptionError").css('color', 'red');

            }
            else {
                $("#descriptionError").hide();
                descriptionError = true;
            }
            return descriptionError;
        }

        // 4. link with Action Event

        $("#orderCode").keyup(function () {
            $(this).val($(this).val().toUpperCase());
            validate_orderCode();
        });

        $("#stId").change(function () {
            validate_stId();
        });

        $("#customerId").change(function () {
            validate_customerId();
        });

        $("#refNum").keyup(function () {
            validate_refNum();
        });

        $('[name="stockMode"]').change(function () {

            validate_stockMode();
        });

        $("#stockSource").change(function () {


            validate_stockSource();
        });

        $("#description").keyup(function () {
            validate_description();
        });

        // 5.click on submit   
        $("#mysaleForm").submit(function () {

            validate_orderCode();
            validate_stId();
            validate_customerId();
            validate_refNum();
            validate_stockMode();
            validate_stockSource();
            validate_description();

            if (orderCodeError && stIdError && customerIdError && refNumError && stockModeError
                && stockSourceError && descriptionError)
                return true;
            else return false;

        });
        
        });