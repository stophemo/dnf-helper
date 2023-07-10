const loginForm = document.getElementById('loginForm')

let APP_ID = "21000127";
let DAID = "8";
loginForm.addEventListener('submit', (event) => {
    event.preventDefault()

    // APP_ID = loginForm.elements.username.value
    // DAID = loginForm.elements.password.value

    // todo 账号密码验证
    if (APP_ID!=null && DAID!=null) {
        // 登录成功后，向主进程发送消息
        window.versions.loginSuccess()
    } else {
        window.alert("账号或密码错误，请重试！");
    }
})

let qrCodeUrl = '';
function generateQrCodeUrl() {
    let t = Math.random().toString();
    return "https://ssl.ptlogin2.qq.com/ptqrshow?appid=" + APP_ID + "&e=2&l=M&s=3&d=72&v=4&t=" + t + "&daid=" + DAID + "&pt_3rd_aid=0";
}

function refreshQrCode() {
    // 生成新的二维码URL
    qrCodeUrl = generateQrCodeUrl();
    // 将img标签的src属性设置为新的二维码URL
    document.getElementById('qrCodeImg').src = qrCodeUrl;
}

// 环境版本
const information = document.getElementById('info')
information.innerText = `本应用正在使用 Chrome (v${versions.chrome()}), Node.js (v${versions.node()}), 和 Electron (v${versions.electron()})`