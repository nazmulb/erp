function addMoreItem() {
    var cnt = jQuery("#cntInputs").val();
    ++cnt;
    jQuery("#cntInputs").val(cnt);
    
    jQuery("#cloneDiv select").attr("name", "pid_"+cnt);
    jQuery("#cloneDiv input").attr("name", "qty_"+cnt)
    
    var item = jQuery("#cloneDiv").html();
    
    jQuery("#itemToCloneDiv").append(item);
}

function addRequiredItem(id) {
    var chk = "[name=chk_"+id+"]";
    var qty = "[name=qty_"+id+"]";
    var rate = "[name=rate_"+id+"]";
    var itmqty = "[name=itmqty_"+id+"]";
    
    if(jQuery(chk).is(':checked')){
        itmqty = jQuery(itmqty).val();
        jQuery(qty).addClass("required").val(itmqty);
        jQuery(rate).addClass("required");
    }else{
        jQuery(qty).removeClass("required").val("");
        jQuery(rate).removeClass("required").val("");
    }  
}