import{ createRouter, createWebHashHistory} from "vue-router"
import helloWorld from '../views/HelloWorld'
import findUser from '../views/FindUser'
import UpdateUser from '../views/UpdateUser'
const routes = [
    {
        path:'/register',
        component:helloWorld
    },
    {
        path:'/find',
        component:findUser
    },
    {
        path:'/update',
        component:UpdateUser
    }
]
const router = createRouter({
    history:createWebHashHistory(),
    routes
})

export default router;