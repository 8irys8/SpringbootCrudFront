async function  getAllUsers() {
    let response = await fetch('/api/users',{
        method: 'GET'
    });

    return response.json();
}

async function  deleteUserById(id) {
    await fetch('/api/users/'+id,{
        method: 'DELETE'
    });

    await reloadTable();
}

async function  getUser(id) {
    let response = await fetch('/api/users/'+id,{
        method: 'GET'
    });

    return response.json();
}

async function  getUserById(id) {
    showAddUserRole();

    let userData = await getUser(id);

    $('#id_edit').val(userData.id);
    $('#username_edit').val(userData.username);
    // $('#password_edit').val(userData.password);
    $('#firstName_edit').val(userData.firstname);
    $('#lastName_edit').val(userData.lastname);
    $('#address_edit').val(userData.address);
    $('#contact_edit').val(userData.contact);
    $('#email_edit').val(userData.email);
}

function getFormData($form){
    let unindexed_array = $form.serializeArray();
    let indexed_array = {};

    $.map(unindexed_array, function(n, i){
        indexed_array[n['name']] = n['value'];
    });

    return indexed_array;
}

//let roleId = $('#roles').val();

async function  addUser() {
    let form = $('#user_form');
    let roleId = $('#roles').val();
    console.log(JSON.stringify(getFormData(form)));
    await fetch('/api/users/'+roleId,{
        method: 'POST',
        headers:{
            'Content-Type':'application/json'
        },
        body:JSON.stringify(getFormData(form))
    });

    await reloadTable();

    $("#addUser").modal('hide');
}

async function  editUser() {
    let pass = $('#password_edit').val();
    if (pass == '') {
        alert('Enter password, please!!');
        return;
    }

    let id = $('#roles_id').val();
    let form = $('#user_edit_form');

    console.log(JSON.stringify(getFormData(form)));

    await fetch('/api/users/'+id,{
        method: 'PUT',
        headers:{
            'Content-Type':'application/json'
        },
        body:JSON.stringify(getFormData(form))
    });

    await reloadTable();

    $("#editUser").modal('hide');
}

function reloadTable() {
    return getAllUsers().then((users) => {
        let table = document.getElementById("user_table");

        table.innerHTML = '';

        let row = table.insertRow(0);
        row.innerHTML = '<tr>\n' +
        '        <th>ID</th>\n' +
        '        <th>First Name</th>\n' +
        '        <th>Last Name</th>\n' +
        '        <th>Address</th>\n' +
        '        <th>Phone number</th>\n' +
        '        <th>Email</th>\n' +
        '        <th>Username</th>\n' +
        '        <th>Actions</th>\n' +
        '    </tr>';

        users.forEach((u) => {
            let row = table.insertRow(1);


            row.insertCell(0).innerText = u.id;
            row.insertCell(1).innerText = u.firstname;
            row.insertCell(2).innerText = u.lastname;
            row.insertCell(3).innerText = u.address;
            row.insertCell(4).innerText = u.contact;
            row.insertCell(5).innerText = u.email;
            row.insertCell(6).innerText = u.username;
            row.insertCell(7).innerHTML = '<a onclick="getUserById('+u.id+');" style="margin-left: 10px;" class="btn btn-primary">Edit</a>\n' +
                '            <a onclick="deleteUserById('+u.id+')" class="btn btn-danger">Delete</a>';
        })
    });

}
async function  getAllRoles() {
    let response = await fetch('/api/roles',{
        method: 'GET'
    });

    return response.json();
}
async function generateRole(elementId) {
    $("#"+elementId).empty();
    let select = document.getElementById(elementId);
    let roles = await getAllRoles();
    console.log(roles);
    roles.forEach((r) => {
        let opt = document.createElement('option');
        opt.value = r.id;
        opt.text = r.role;
        select.appendChild(opt);
    })
}

function showAddUser() {
    $("#addUser").modal();
    generateRole('roles');
}

function showAddUserRole() {
    $("#editUser").modal();
    generateRole('roles_id');
}

reloadTable();

