import { createContext } from 'react'

export const UserContext = createContext({
  user: {
    name: '',
    id: '',
    image: '',
    playlists: [],
    playlistLikes: [],
  },
  setUser: (u) => {
    u
  },
})
