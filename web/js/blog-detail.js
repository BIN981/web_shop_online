


function GetDetail(blogId){
    $.ajax({
        url: 'blogDetail',
        data: {
            blogId: blogId
        },
        success: function (responseText) {
            if(responseText !== null){
                $('.post__list').remove();
                $('.posts').append(responseText);
            }
        }
    });
}


