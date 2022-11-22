const buttonCancel = document.querySelector(".button-cancel");

function Change() {
    if (confirm("Are you want to change this order?")) {
        window.location.assign("cart");
    }
}


function ShowEdit() {
    document.querySelector("#btn-edit").remove();
    document.querySelector(".card_action .button-buy").classList.remove("hide");
    document.querySelector("#input-address").readOnly = false;
    document.querySelector("#input-address").focus();
}

function Submit() {
    if (confirm("Are you want to confirm this order?")) {
        document.querySelector(".user-info").submit();
    }
}

function Cancel(orderId) {
    if (confirm("Are you want to cancel this order?")) {
        window.location.assign(`cancelOrder?id=${orderId}`);
    }
}

function ReceivedOrder(orderId) {
    if (confirm("Had you received this order?")) {
        window.location.assign(`receivedOrder?orderId=${orderId}`);
    }
}

var imagesBase64;
var imageChoose;
var imageInput;

function Feedback(orderId) {
    imagesBase64 = [];
    document.querySelector('.modal').classList.remove("hide");

    $('.modal').html(`
       <form class="feedback-modal" action="addFeedback" method="post">
        <i class="fa-solid fa-xmark modal__close"></i>
        <h1>Feedback</h1>
        <span>Images</span>
        <ul class="feedback__images">
          <li class="add-image">
            <label for="choose-image">
              <i class="fa-solid fa-plus"></i>
              <div>Add Image</div>
            </label>
          </li>
        </ul>

        <input type="text" name="orderId" value="${orderId}" hidden />
        <input id="choose-image" type="file" accept=".jpg,.jpge,.png" hidden />
        <input id="input-image" type="text" name="images" hidden />
        <div id="image-size">Total: 0MB/2MB</div>
        <span>Rating</span>
        <div class="rate">
          <input type="radio" id="star5" name="rate" value="5" checked/>
          <label for="star5" title="text"></label>
          <input type="radio" id="star4" name="rate" value="4" />
          <label for="star4" title="text"></label>
          <input type="radio" id="star3" name="rate" value="3" />
          <label for="star3" title="text"></label>
          <input type="radio" id="star2" name="rate" value="2" />
          <label for="star2" title="text"></label>
          <input type="radio" id="star1" name="rate" value="1" />
          <label for="star1" title="text"></label>
        </div>
        <span>Content</span>
        <textarea name="content" cols="30" rows="10"></textarea>
        <span class="btn-submit" onclick="CheckImageInputSize()">Send</span>
      </form>
`);

    const closeIcon = document.querySelector(".modal__close");
    const modal = document.querySelector(".modal");

    closeIcon.addEventListener("click", e => {
        modal.classList.add("hide");
        modal.innerHTML = "";
    });

    imageChoose = document.querySelector("#choose-image");
    imageInput = document.querySelector("#input-image");

    imageChoose.addEventListener("change", (e) => {
        var reader = new FileReader();
        const MAX_WIDTH = 400;
        const MAX_HEIGHT = 400;
        var resizeImage;
        reader.onload = async function () {
            let resized_base64 = await new Promise((resolve) => {
                let img = new Image();
                img.src = reader.result;
                img.onload = () => {
                    let canvas = document.createElement('canvas');
                    let width = img.width;
                    let height = img.height;

                    if (width > height) {
                        if (width > MAX_WIDTH) {
                            height *= MAX_WIDTH / width;
                            width = MAX_WIDTH;
                        }
                    } else {
                        if (height > MAX_HEIGHT) {
                            width *= MAX_HEIGHT / height;
                            height = MAX_HEIGHT;
                        }
                    }
                    canvas.width = width;
                    canvas.height = height;
                    let ctx = canvas.getContext('2d');
                    ctx.drawImage(img, 0, 0, width, height);
                    resolve(resizeImage = canvas.toDataURL());// this will return base64 image results after resize
                }
            });
            imagesBase64.push(resizeImage);
            let li = document.createElement("li");
            li.classList.add("feedback__image-item");
            li.innerHTML = `
                <i class="fa-solid fa-xmark remove-image" onclick="RemoveImage(event)"></i>
                <img src="${resizeImage}" alt="" />`;
            document.querySelector(".feedback__images").appendChild(li);
            LoadInputImage();
        };
        reader.readAsDataURL(e.target.files[0]);
        imageChoose.value = "";
    });

}
function LoadInputImage() {
    let imgInput = "";
    imagesBase64.forEach(item => {
        imgInput += `&${item}`;
    });
    imageInput.value = imgInput;
    let totalImageSize = GetImageInputSize();
    const imageSize = document.querySelector("#image-size");
    imageSize.innerHTML = `Total: ${totalImageSize}MB/2MB`;
    if (totalImageSize > 2) {
        imageSize.style = "color: red";
    }
}

function RemoveImage(e) {
    if (confirm("You want remove this image?")) {
        e.target.parentElement.remove();
        RemoveBase64Images(e.target.nextElementSibling.src);
    }
}

function RemoveBase64Images(image) {
    const index = imagesBase64.indexOf(image);
    if (index > -1) {
        imagesBase64.splice(index, 1);
        LoadInputImage();
    }
}

function GetImageInputSize() {
    return Math.round((new TextEncoder().encode($("#input-image").val())).length / 1024) / 1000;
}

function CheckImageInputSize() {
    if (GetImageInputSize() > 2) {
        alert("Please select the number of images less than or equal to 2MB");
    } else {
        document.querySelector(".feedback-modal").submit();
    }
}

