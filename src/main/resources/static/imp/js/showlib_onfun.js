    $(function() {
        $("#test").click(function() {
            $("#tettt").dropdown('toggle');
        })
    });
    $('#ycfilter :checkbox[type="checkbox"]').each(function(){
    $(this).click(function(){
            $('#ycfilter :checkbox[type="checkbox"][name='+$(this).attr("name")+']').prop('checked',false);
            $(this).prop('checked',true);
    });
});