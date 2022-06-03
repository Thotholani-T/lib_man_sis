$('document').ready(function(){
    $('table #reserveButton').on('click', function(event){
        event.preventDefault();
        var href = $(this).attr('href');

        $.get(href, function(book, status) {
            $('#id').val(book.id);
            $('#title').val(book.title);
            $('#author').val(book.author);
            $('#category').val(book.category);
            $('#publisher').val(book.publisher);
            $('#edition').val(book.edition);
        });
//        $('#confirmReserveButton').attr('href',href);
        $('#confirmModal').modal('show');
    });

     $('table #reserveButtonView').on('click', function(event){
            event.preventDefault();
            var href = $(this).attr('href');

            $.get(href, function(book, status) {
                $('#id').val(book.id);
                $('#title').val(book.title);
                $('#author').val(book.author);
                $('#category').val(book.category);
                $('#publisher').val(book.publisher);
                $('#edition').val(book.edition);
            });
    //        $('#confirmReserveButton').attr('href',href);
            $('#confirmModal').modal('show');
        });

    $('table #viewButton').on('click', function(event){
            event.preventDefault();
            var href = $(this).attr('href');

            $.get(href, function(book, status) {
                $('#idEdit').val(book.id);
                $('#titleEdit').val(book.title);
                $('#authorEdit').val(book.author);
                $('#categoryEdit').val(book.category);
                $('#publisherEdit').val(book.publisher);
                $('#editionEdit').val(book.edition);
            });
            $('#viewModal').modal('show');
        });
});