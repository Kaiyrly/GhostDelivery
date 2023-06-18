import { useState } from 'react'


export default function useToken() {
  const getToken = () => {
    const tokenString = localStorage.getItem('user')
    return tokenString ? JSON.parse(tokenString)?.token : undefined
  }

  const [token, setToken] = useState(getToken())

  const saveToken = (userToken) => {
    localStorage.setItem('user', JSON.stringify(userToken))
    setToken(userToken.token)
  }

  return {
    setToken: saveToken,
    token
  }
}