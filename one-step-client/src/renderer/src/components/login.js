const loginForm = document.getElementById('loginForm')
loginForm.addEventListener('submit', (event) => {
    event.preventDefault()

    const username = loginForm.elements.username.value
    const password = loginForm.elements.password.value

    // TODO: 在这里进行登录验证
    const loginResult = 'success'
    // 登录成功后，向主进程发送消息
    window.versions.loginSuccess(loginResult)
})

const information = document.getElementById('info')
information.innerText = `本应用正在使用 Chrome (v${versions.chrome()}), Node.js (v${versions.node()}), 和 Electron (v${versions.electron()})`