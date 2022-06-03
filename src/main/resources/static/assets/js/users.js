$('document').ready(function() {
    $('table #editButton').on('click', function(event) {
        event.preventDefault();
        var href = $(this).attr('href');

        $.get(href, function(user, status) {
            $('#libraryIdEdit').val(user.libraryId);
            $('#firstNameEdit').val(user.firstName);
            $('#lastNameEdit').val(user.lastName);
            $('#emailAddressEdit').val(user.emailAddress);
            $('#phoneNumberEdit').val(user.phoneNumber);
            $('#addressEdit').val(user.address);
            $('#roleEdit').val(user.role);
            $('#passwordEdit').val(user.password);
        });
        $('#editModal').modal('show');
    });

    $('table #deleteButton').on('click', function(event) {
        event.preventDefault();
        var href = $(this).attr('href');
        $('#confirmDeleteButton').attr('href', href);
        $('#deleteModal').modal('show');
    });
});