async function  getUser() {
    let response = await fetch('/api/users/info',{
        method: 'GET'
    });

    return response.json();
}


function reloadTable() {
    return getUser().then((user) => {
        console.log(user);

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
            '    </tr>';

            row = table.insertRow(1);


            row.insertCell(0).innerText = user.id;
            row.insertCell(1).innerText = user.firstname;
            row.insertCell(2).innerText = user.lastname;
            row.insertCell(3).innerText = user.address;
            row.insertCell(4).innerText = user.contact;
            row.insertCell(5).innerText = user.email;
            row.insertCell(6).innerText = user.username;
    });
}

reloadTable();