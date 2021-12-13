import SearchBar from 'components/molecules/SearchBar'
import { PlayerContext } from 'context/playerContext'
import React, { useState } from 'react'
import { Outlet } from 'react-router-dom'
import { Player } from 'react-yt-sound-player'
import './MainScreen.css'

const MainScreen = () => {
  const [player, setPlayer] = useState({
    songs: [],
    start: 0,
  })

  console.log(player)

  return (
    <PlayerContext.Provider value={{ player, setPlayer }}>
      <div className='mainScreenContainer'>
        <div className='topContainer'>
          <SearchBar />
        </div>
        <div className='middleContainer'>
          <Outlet />
        </div>
        <div className='bottomContainer'>
          <Player
            songs={player.songs.map((song) => song.url.substring(32))}
            start={player.start}
          />
        </div>
      </div>
    </PlayerContext.Provider>
  )
}

export default MainScreen
