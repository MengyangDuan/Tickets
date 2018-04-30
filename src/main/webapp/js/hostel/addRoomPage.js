/**
 * Created by disinuo on 17/3/13.
 */
$('#addRoomForm').submit(function (e) {
    var url='/hostel/addRoom';
    var data={
        name:$('#name').val(),
        price:$('#price').val(),
        img:$('#img').val(),
        capacity:$('#capacity').val(),
        totalNum:$('#totalNum').val(),
        startDate:$('#startDate').val(),
        endDate:$('#endDate').val(),
        descrip:$('#descrip').val()
    };
    data.img='/../img/hostel002.jpg';
    $.ajax({
        type:'POST',
        url:url,
        data:data,
        success:function (data) {
            $('#msg').html(data);
        },
        error:function (data) {
            alert('ERROR!!!: '+JSON.stringify(data));
        }
    });
    e.preventDefault(); // avoid to execute the actual submit of the form.
});
$(function () {
    $("#startDate").flatpickr();
    $("#endDate").flatpickr();
});
