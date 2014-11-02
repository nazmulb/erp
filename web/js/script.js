function addMoreItem() {
    var cnt = jQuery("#cntInputs").val();
    var preCnt = cnt;
    ++cnt;
    jQuery("#cntInputs").val(cnt);
    
    var pid = "#cloneDiv [name=pid_"+preCnt+"]";
    var qty = "#cloneDiv [name=qty_"+preCnt+"]";
    var rate = "#cloneDiv [name=rate_"+preCnt+"]";
    var total = "#cloneDiv [name=total_"+preCnt+"]";
    
    jQuery(pid).attr("name", "pid_"+cnt);
    jQuery(qty).attr("name", "qty_"+cnt);
    
    if(jQuery(rate).length > 0){
        jQuery(rate).attr("name", "rate_"+cnt);
    }
    
    if(jQuery(total).length > 0){
        jQuery(total).attr("name", "total_"+cnt); 
    }
    
    var item = jQuery("#cloneDiv").html();
    
    jQuery("#itemToCloneDiv").append(item);
}

function addRequiredItem(id) {
    var chk = "[name=chk_"+id+"]";
    var qty = "[name=qty_"+id+"]";
    var rate = "[name=rate_"+id+"]";
    var itmqty = "[name=itmqty_"+id+"]";
    
    if(jQuery(".chk-class").is(':checked')){
       jQuery("#btnSubmit").removeAttr('disabled');
    }else{
        jQuery("#btnSubmit").attr('disabled', 'disabled');
    }
    
    if(jQuery(chk).is(':checked')){
        itmqty = jQuery(itmqty).val();
        jQuery(qty).addClass("required").val(itmqty);
        jQuery(rate).addClass("required");
    }else{
        jQuery(qty).removeClass("required").val("");
        jQuery(rate).removeClass("required").val("");
    }  
}

function checkUname(uname) {
    var method    = "POST";
    var url 	  = "user?action=check_uname";
    var queryStr  = {uname: uname};

    jQuery.ajax({
        type:    method,
        url:     url,
        data:	 queryStr,
        success: function(msg){
            if(msg==1){
                alert("User name already used. Please try another.");
            }else{
                document.userForm.submit();
                return true;
            }
        }
    });
}

function updateTotal() {
    var cnt = jQuery("#cntInputs").val();
    
    var qtyObj = "[name=qty_1]";
    var rateObj = "[name=rate_1]";
    var totalObj = "[name=total_1]";
    qty = 0, rate = 0, total = 0, subtotal = 0, vatper = 0, vat = 0, grandTotal = 0;
    
    for(var i = 1; i<=cnt; i++){
        if(i>1){
            qtyObj = "#itemToCloneDiv [name=qty_"+i+"]";
            rateObj = "#itemToCloneDiv [name=rate_"+i+"]";
            totalObj = "#itemToCloneDiv [name=total_"+i+"]";   
        }
        
        if(jQuery(qtyObj).length > 0){
            qty = parseFloat(jQuery(qtyObj).val());
            rate = parseFloat(jQuery(rateObj).val());
            total = parseFloat(qty * rate);
            jQuery(totalObj).val(total);
            subtotal = parseFloat(subtotal + total);
        }
    }
    
    jQuery("[name=subtotal]").val(subtotal);
}

function updateVat() {
    
    alert("updateVat");
}