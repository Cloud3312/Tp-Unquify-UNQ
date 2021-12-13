import Axios from 'axios'
import {
  playlistFrom,
  shortUserFrom,
  userFrom,
} from 'utils/transformationUtils'

const baseUrl = 'http://localhost:7000'

const loginUser = (email, password) => {
  return Axios.post(`${baseUrl}/login`, { email, password }).then(
    (response) => {
      localStorage.setItem('token', response.headers.authentication)
      return userFrom(response.data)
    }
  )
}

const userInfo = () => {
  const token = localStorage.getItem('token')
  return Axios.get(`${baseUrl}/user`, {
    headers: { Authentication: token },
  }).then((response) => {
    return userFrom(response.data)
  })
}

const userById = (userId) => {
  const token = localStorage.getItem('token')
  return Axios.get(`${baseUrl}/user/${userId}`, {
    headers: { Authentication: token },
  }).then((response) => {
    return userFrom(response.data)
  })
}

const registerUser = (displayName, email, password, image) => {
  return Axios.post(`${baseUrl}/register`, {
    displayName,
    email,
    password,
    image,
  }).then((response) => {
    localStorage.setItem('token', response.headers.authentication)
    return userFrom(response.data)
  })
}

const playlist = (playlistId) => {
  const token = localStorage.getItem('token')
  return Axios.get(`${baseUrl}/playlist/${playlistId}`, {
    headers: { Authentication: token },
  }).then((response) => {
    return playlistFrom(response.data)
  })
}

const playlistAddOrRemove = (playlistId) => {
  const token = localStorage.getItem('token')
  return Axios.put(
    `${baseUrl}/playlist/${playlistId}`,
    {},
    {
      headers: { Authentication: token },
    }
  ).then((response) => {
    return userFrom(response.data)
  })
}

const searcher = (query) => {
  const token = localStorage.getItem('token')
  return Axios.get(`${baseUrl}/search?q=${query}`, {
    headers: { Authentication: token },
  }).then((response) => {
    return {
      playlists: response.data.playlists.map((playlist) =>
        playlistFrom(playlist)
      ),
      songs: response.data.songs,
      users: response.data.users.map((user) => shortUserFrom(user)),
    }
  })
}

export default {
  loginUser,
  userInfo,
  registerUser,
  playlist,
  playlistAddOrRemove,
  searcher,
  userById,
}
