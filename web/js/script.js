function addMoreItem() {
    var cnt = jQuery("#cntInputs").val();
    ++cnt;
    jQuery("#cntInputs").val(cnt);
    
    jQuery("#cloneDiv select").attr("name", "pid_"+cnt);
    jQuery("#cloneDiv input").attr("name", "qty_"+cnt)
    
    var item = jQuery("#cloneDiv").html();
    
    jQuery("#itemToCloneDiv").append(item);
}