const { ipcRenderer } = require('electron')

const form = document.querySelector('form')

form.addEventListener('submit', (event) => {
    event.preventDefault()

    const username = document.getElementById('username').value
    const password = document.getElementById('password').value

    // TODO: 根据用户名和密码验证用户
    if (username === password) {
        ipcRenderer.send('login-successful')
    }
})

ipcRenderer.on('show-main-window', () => {
    window.close()
})