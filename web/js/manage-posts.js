
function EditPost(postId) {
    document.querySelector('.modal').classList.remove("hide");
    $.ajax({
        url: 'editPost',
        data: {
            postId: postId
        },
        success: function (responseText) {
            $('.modal').html(responseText);
            const closeIcon = document.querySelector(".modal__close");
            const modal = document.querySelector(".modal");


            closeIcon.addEventListener("click", e => {
                modal.classList.add("hide");
                modal.innerHTML = "";
            });

            const thumbnailInput = document.querySelector(".modal__blog-image");
            const thumbnail = document.querySelector("#previewThumbnail");

            thumbnailInput.addEventListener('change', (e) => {
                var reader = new FileReader();
                reader.onload = function () {
                    thumbnail.src = reader.result;
                    document.querySelector("#image-base64").value = reader.result;
                }
                reader.readAsDataURL(e.target.files[0]);
            });
        }
    });
}
function ViewPost(postId) {
    document.querySelector('.modal').classList.remove("hide");
    $.ajax({
        url: 'viewPost',
        data: {
            postId: postId
        },
        success: function (responseText) {
            $('.modal').html(responseText);
            
            var closeButtons = document.querySelector('.close-icon');
            closeButtons.addEventListener('click', function () {
                document.querySelector('.modal').innerHTML = "";
                document.querySelector('.modal').classList.add("hide");
            });

        }
    });
}


function AddPost() {
    document.querySelector('.modal').classList.remove("hide");

    $('.modal').html(`
       <form action="addPost" method="post" class="edit-modal">
       <i class="fa-solid fa-xmark modal__close"></i>
       <img id="previewThumbnail" src="image/logo.png" alt="">
       <span>Thumbnail</span>
       <input class="modal__blog-image" type="file" required>
       <input id="image-base64" type="text" name="image" hidden>
       <span>Category</span>
       <select name="category">
        <option value="1" selected>Thời trang</option>
        <option value="2">Đời sống</option>
        <option value="3">Thể thao</option>
       </select>
       <span>Title</span>
       <input type="text" class="modal__blog-title" name="title" title="Blog Title" required>
       <span>Description</span>
       <textarea class="modal__blog-description" name="description" 
        title="Blog Description" rows="10" required></textarea>
        <button type="submit">Add Post</button>
      </form>
`);
    const closeIcon = document.querySelector(".modal__close");
    const modal = document.querySelector(".modal");


    closeIcon.addEventListener("click", e => {
        modal.classList.add("hide");
        modal.innerHTML = "";
    });

    const thumbnailInput = document.querySelector(".modal__blog-image");
    const thumbnail = document.querySelector("#previewThumbnail");

    thumbnailInput.addEventListener('change', (e) => {
        var reader = new FileReader();
        reader.onload = function () {
            thumbnail.src = reader.result;
            document.querySelector("#image-base64").value = reader.result;
        }
        reader.readAsDataURL(e.target.files[0]);
    });
}

function UpdateStatusPost(postId, status) {
    $.ajax({
        url: 'updateStatusPost',
        data: {
            postId: postId,
            status: !status
        },
        success: function (responseText) {
            window.location.assign("managePosts");
        }
    });
}





