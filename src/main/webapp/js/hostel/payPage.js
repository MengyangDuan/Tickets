/**
 * Created by disinuo on 17/3/13.
 */
$("#payForm").submit(function(e) {
    var url = "/hostel/pay";
    var data={
        vipId:$('#vipId').val(),
        money:$('#money').val()
    };
    if($('#vipId').val()=="")data.vipId=0;
    $.ajax({
        type: "POST",
        url: url,
        // data: $("#idForm").serialize(),
        data: data, // serializes the form's elements.
        success: function(data) {
            $('#msg').html(data);
        },
        error:function (data) {
            alert('ERROR!!!: '+JSON.stringify(data));
        }
    });

    e.preventDefault(); // avoid to execute the actual submit of the form.
});