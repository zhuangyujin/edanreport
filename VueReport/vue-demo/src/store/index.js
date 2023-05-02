import{ createStore} from 'vuex'
//全局状态信息管理
export default createStore({
    state:{
        token : localStorage.getItem("token") || ''
    }
})