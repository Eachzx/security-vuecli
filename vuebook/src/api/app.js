import axios from '@/libs/api.request.js'


export const login = ({ userName, password }) => {
  const data = {
    userName,
    password
  }
  return axios.request({
    url: 'login',
    data,
    method: 'post'
  })
}

export const select = ({name,telephone},{current, size}) => {
  return axios.request({
    url: 'users/page',
    params: {
      name,
      telephone,
      current,
      size
    },
    method: 'get'
  })
}

export const update = (users) => {
  return axios.request({
    url: 'users/update',
    params:users,
    method: 'put'
  })
}

export const deletes = (users) => {
  return axios.request({
    url: 'users/delete',
    params:users,
    method: 'delete'
  })
}

export const insert = (users) => {
  return axios.request({
    url: 'users/insert',
    params:users,
    method: 'post'
  })
}

export const exportexcel = ({name,telephone},{current, size}) => {
  return axios.request({
    url: 'users/exportexcel',
    params: {
      name,
      telephone,
      current,
      size
    },
    method: 'get',
    responseType:'blob'
  })
}