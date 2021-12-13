import LoginScreen from 'components/screens/LoginScreen'
import MainScreen from 'components/screens/MainScreen'
import ListResultView from 'components/views/ListResultView'
import NotFoundView from 'components/views/NotFoundView'
import PlaylistView from 'components/views/PlaylistView'
import UserView from 'components/views/UserView'
import { UserContext } from 'context/userContext'
import React, { useEffect, useState } from 'react'
import { Outlet, Route, Routes } from 'react-router-dom'
import spotifyApi from 'services/spotifyApi'
import RegisterScreen from './components/screens/RegisterScreen'
import WelcomeScreen from './components/screens/WelcomeScreen'

const App = () => {
  const [user, setUser] = useState({
    name: '',
    id: '',
    image: '',
    playlists: [],
    playlistLikes: [],
  })

  useEffect(() => {
    if (localStorage.getItem('token')) {
      spotifyApi.userInfo().then((userData) => {
        setUser(userData)
      })
    }
  }, [])

  return (
    <UserContext.Provider value={{ user, setUser }}>
      <Routes>
        <Route path='/' element={user.id ? <MainScreen /> : <WelcomeScreen />}>
          <Route index element={<UserView />} />
          <Route path='404' element={<NotFoundView />} />
          <Route path='search' element={<Outlet />}>
            <Route path=':searchText' element={<ListResultView />} />
          </Route>
          <Route path='user' element={<Outlet />}>
            <Route path=':userId' element={<UserView />} />
          </Route>
          <Route path='playlist' element={<Outlet />}>
            <Route path=':playlistId' element={<PlaylistView />} />
          </Route>
        </Route>
        <Route path='/login' element={<LoginScreen />} />
        <Route path='/register' element={<RegisterScreen />} />
      </Routes>
    </UserContext.Provider>
  )
}

export default App
