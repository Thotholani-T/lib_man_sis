$('document').ready(function() {
    $('table #editButton').on('click', function(event) {
        event.preventDefault();
        var href = $(this).attr('href');

        $.get(href, function(librarian, status) {
            $('#libraryIdEdit').val(librarian.libraryId);
            $('#firstNameEdit').val(librarian.firstName);
            $('#lastNameEdit').val(librarian.lastName);
            $('#emailAddressEdit').val(librarian.emailAddress);
            $('#phoneNumberEdit').val(librarian.phoneNumber);
            $('#addressEdit').val(librarian.address);
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