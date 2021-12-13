import React, { useContext, useState } from 'react'
import { useNavigate } from 'react-router-dom'
import spotifyLogo from '../../assets/spotifyLogo.png'
import './SearchBar.css'
import { UserContext } from '../../context/userContext'

const SearchBar = () => {
  const navigate = useNavigate()

  const [searchText, setSearchText] = useState('')

  const handleChangeText = (event) => {
    setSearchText(event.target.value)
  }

  const goHome = () => {
    navigate('/')
  }

  const goToSearch = () => {
    navigate(`/search/${searchText}`)
  }

  const { setUser } = useContext(UserContext)

  const logoutUser = () => {
    localStorage.removeItem('token')
    setUser({
      name: '',
      id: '',
      image: '',
      playlists: [],
      playlistLikes: [],
    })
  }

  return (
    <div>
      <div className='topnav'>
        <a className='active' onClick={goHome}>
          Home
        </a>
        <img src={spotifyLogo} alt='' onClick={goHome} />
        <div className='topnav-right-area'>
          <div className='searchContainer'>
            <input
              value={searchText}
              placeholder='Users, playlists or songs'
              onChange={handleChangeText}
              className='search'
            ></input>
            <button type='button' onClick={goToSearch} className='searchButton'>
              🔎
            </button>
          </div>
          <div className='buttonContainer'>
            <a className='active' onClick={logoutUser}>
              Logout
            </a>
          </div>
        </div>
      </div>
    </div>
  )
}

export default SearchBar
