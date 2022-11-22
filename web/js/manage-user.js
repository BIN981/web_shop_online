const modal = document.querySelector(".modal");

function EditUser(userId) {
    $.ajax({
        url: "editUser",
        data: {
            userId: userId
        },
        success: function (responseText) {
            modal.classList.remove("hide");
            modal.innerHTML = responseText;

            const closeIcon = document.querySelector(".edit-modal > i");

            closeIcon.addEventListener("click", (e) => {
                modal.classList.add("hide");
                modal.innerHTML = "";
            });

            const editModal = document.querySelector(".edit-modal");
            const roleSelect = document.querySelector(".edit-modal select");
            const roleOptions = document.querySelectorAll(".edit-modal select option");
            const btnSaveModal = document.querySelector(".edit-modal button");

            var currentRole;
            roleOptions.forEach((item) => {
                if (item.selected) {
                    currentRole = item.value;
                    console.log(currentRole);
                }
            });

            roleSelect.addEventListener("change", (e) => {
                if (e.target.value !== currentRole) {
                    console.log("role changed " + e.target.value);
                    btnSaveModal.classList.remove("disable");
                    btnSaveModal.disabled = false;
                } else {
                    if (!btnSaveModal.classList.contains("disable")) {
                        btnSaveModal.classList.add("disable");
                        btnSaveModal.disabled = true;
                    }
                }
            });
        }

    });
}

function AddUser() {
    modal.classList.remove("hide");
    modal.innerHTML = `<form class="edit-modal" action="AddUserController" method="post">
        <i class="fa-solid fa-xmark"></i>
        <h2 style="margin-bottom: 30px">Add User</h2>
        <div class="modal-name">
          <span style="margin-right: 10px">Username</span><input type="text" class="modal-name" name="username" required/>
        </div>
        <div class="modal-name">
          <span style="margin-right: 10px">Password</span><input type="password" class="modal-name" name="password" required/>
        </div>
        <div class="modal-name">
          <span style="margin-right: 10px">Email</span><input type="email" class="modal-name" name="email" required/>
        </div><div class="modal-name">
          <span style="margin-right: 10px">Role</span>
          <select name="role">
            <option value="1">Marketing Manager</option>
            <option value="2">Marketing</option>
            <option value="3">Sale</option>
            <option value="4" selected>Customer</option>
          </select>
        </div>
        <button class="btn-save" type="submit">Add</button>
      </form>`;

    const closeIcon = document.querySelector(".edit-modal > i");

    closeIcon.addEventListener("click", (e) => {
        modal.classList.add("hide");
        modal.innerHTML = "";
    });

}



window.onbeforeunload = function () {
    localStorage.setItem("name", $('#name').val());
    localStorage.setItem("role", $('#role').val());
};

window.onload = function () {
    if (localStorage.getItem("name") !== null)
        $('#name').val(localStorage.getItem("name"));
    if (localStorage.getItem("role") !== null)
        $('#role').val(localStorage.getItem("role"));

};


