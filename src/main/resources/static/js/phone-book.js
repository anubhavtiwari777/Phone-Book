$(document).ready(function () {

    $('.collapsible').collapsible();

    var contacts = [];
    var contactFilter = $('#contact_filter');


    getAllContacts();

    function getAllContacts() {
        $.ajax({
            type: 'GET',
            url: getBaseUrl() + '/contacts',
            success: function (data) {
                $(document.body).show();
                $('#nav-mobile').show();
                contacts = data;
                contacts.forEach(function (contact) {
                    generateContactItem(contact);
                })
            },
            error: function (xhr, str) {
                console.log(xhr);
                window.location.href = getBaseUrl() + '/index.html';
            }
        });
    }

    // Function for generating contact list

    function generateContactItem(contact) {
        var phones = getPhoneNumbers(contact);
        var phoneBookList = document.querySelector('.phone-book-list');
        var contactListItem = document.createElement('li');
        contactListItem.className = 'contact';
        contactListItem.id = `contact-item-${contact.id}`;
        contactListItem.innerHTML = `
            <div class="collapsible-header">
                <i class="material-icons">phone</i>
                <span id="contact-${contact.id}-name">[Name]: ${contact.name}  [Address]: ${contact.address}  [Numbers]: ${phones} </span>
            </div>`;
        phoneBookList.appendChild(contactListItem);
        Materialize.updateTextFields();
    }

    function getPhoneNumbers(contact) {
        var phones = "~ ";
        var numbers  = contact.phoneNumbers;
        numbers.forEach(function (number) {
            var singleNumber  ="" + number.phoneNumber +" ~  ";
            phones = phones + singleNumber;
        });
        return phones;
    }

    contactFilter.on('keyup', function (event) {
        console.log(event.target.value);
        filterContacts(event.target.value);
    });

    function filterContacts(value) {
        document.querySelector('.phone-book-list').innerHTML = '';
        getAllValidateContacts(value);
    }

    function getAllValidateContacts(parameter) {
        var requestData = {
            "string": parameter
        };

        $.ajax({
            type: 'GET',
            url: getBaseUrl() + '/contacts/validate',
            data: requestData,
            success: function (data) {
                console.log(data);
                contacts = data;
                contacts.forEach(function (contact) {
                    generateContactItem(contact);
                })
            },
            error: function (xhr, str) {
                console.log(xhr);
            }
        });
    }
});

