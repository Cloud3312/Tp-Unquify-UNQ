import { createContext } from 'react'

export const PlayerContext = createContext({
  player: {
    songs: [],
    start: 0,
  },
  setPlayer: (p) => p,
})
