
function EditSlider(sliderId) {
    document.querySelector('.modal').classList.remove("hide");
    $.ajax({
        url: 'editSlider',
        data: {
            sliderId: sliderId
        },
        success: function (responseText) {
            $('.modal').html(responseText);
            const closeIcon = document.querySelector(".modal__close");
            const modal = document.querySelector(".modal");


            closeIcon.addEventListener("click", e => {
                modal.classList.add("hide");
                modal.innerHTML = "";
            });

            const thumbnailInput = document.querySelector(".modal__alider-image");
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

function AddSlider() {
    document.querySelector('.modal').classList.remove("hide");

    $('.modal').html(`
       <form action="addSlider" method="post" class="edit-modal">
       <i class="fa-solid fa-xmark modal__close"></i>
       <img id="previewThumbnail" src="image/logo.png" alt="">
       <span>Thumbnail</span>
       <input class="modal__alider-image" type="file" required>
       <input id="image-base64" type="text" name="image" hidden>     
       </select>
       <span>Title</span>
       <input type="text" class="modal__slider-title" name="title" title="Slider Title" required>
       <span>Description</span>
       <textarea class="modal__slider-description" name="description" 
        title="Slider Description" rows="10" required></textarea>
        <button type="submit">Add Slider</button>
      </form>
`);
    const closeIcon = document.querySelector(".modal__close");
    const modal = document.querySelector(".modal");


    closeIcon.addEventListener("click", e => {
        modal.classList.add("hide");
        modal.innerHTML = "";
    });

    const thumbnailInput = document.querySelector(".modal__alider-image");
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

function UpdateStatusSlider(sliderId, status) {
    $.ajax({
        url: 'updateStatusSlider',
        data: {
            sliderId: sliderId,
            status: !status
        },
        success: function (responseText) {
            window.location.assign("manageSliders");
        }
    });
}






