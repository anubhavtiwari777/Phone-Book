$(document.body).show();
var addForm = $('.add-form');

addForm.submit(function (event) {
    event.preventDefault();
    var contactData = {
        "name": $('#name').val().trim(),
        "address": $('#address').val().trim(),
        "newPhoneNumber": $('#phone').val().trim()
    };
    $.ajax({
        contentType: 'application/json',
        type: 'POST',
        dataType: 'json',
        url: getBaseUrl() + '/contacts',
        data: JSON.stringify(contactData),
        success: function (data) {
            console.log(data);
            window.location.href = getBaseUrl() + '/phone-book.html';
        }, error: function (xhr, str) {
            console.log(xhr)
            var errorResponse = JSON.parse(xhr.responseText).description;
            alertify.error(errorResponse);
        }
    });
});