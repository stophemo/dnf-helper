const { contextBridge,ipcRenderer } = require('electron')

contextBridge.exposeInMainWorld('versions', {
    node: () => process.versions.node,
    chrome: () => process.versions.chrome,
    electron: () => process.versions.electron,
    // 除函数之外，我们也可以暴露变量
    loginSuccess: (res) => ipcRenderer.send('login-successful',res),
    setTitle: (title) => ipcRenderer.send('set-title', title)
})