import axios   from "./request"
import path from "./path"
const api ={
    read(data){
        console.log(data)
        return axios.get(path.baseUrl+'/user/read',{
            headers:{
                Token:data
            }
        })
    },
    update(data){
        console.log("update+++++",data)
        return axios.put(path.baseUrl+'/user/update',{
            userName: data.userName,
            userId: data.userId,
            userpassword: data.userpassword
        },{
            headers:{
                Token:data.token
            }
        })
    },
    delete(data){
        console.log("delete")
        console.log(data)
       return axios.delete(path.baseUrl+'/user/delete',{
            headers:{
                Token:data.token
            },
            data:{
                userId: data.userId,
            }
       })
    }
}
export default api