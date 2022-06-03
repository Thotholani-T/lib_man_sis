$('document').ready(function(){
    $('table #editButton').on('click', function(event){
        event.preventDefault();
        var href = $(this).attr('href');

        $.get(href, function(book, status) {
            $('#idEdit').val(book.id);
            $('#titleEdit').val(book.title);
            $('#authorEdit').val(book.author);
            $('#categoryEdit').val(book.category);
            $('#publisherEdit').val(book.publisher);
            $('#editionEdit').val(book.edition);
            $('#priceEdit').val(book.price);
            $('#copiesEdit').val(book.copies);
        });
        $('#editModal').modal('show');
    });

    $('table #deleteButton').on('click', function(event){
        event.preventDefault();
        var href = $(this).attr('href');
        $('#confirmDeleteButton').attr('href',href);
        $('#deleteModal').modal('show');
    });
});