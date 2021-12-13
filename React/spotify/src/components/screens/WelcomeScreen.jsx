import React, { Fragment } from 'react'
import { useNavigate } from 'react-router-dom'
import './WelcomeScreen.css'

const WelcomeScreen = () => {
  const navigate = useNavigate()
  return (
    <Fragment>
      <div className='welcome-container'>
        <nav className='welcome-navbar'>
          <div className='left-navbar'>
            <img
              className='navbar-logo'
              src='https://img.icons8.com/fluency/48/000000/spotify.png'
            />
            <h1>Spotify</h1>
          </div>
          <div className='right-navbar'>
            <button
              className='navbar-link'
              onClick={() => {
                navigate('/register')
              }}
            >
              Sign up
            </button>
            <button
              className='navbar-link'
              onClick={() => {
                navigate('/login')
              }}
            >
              Log in
            </button>
          </div>
        </nav>
        <div className='welcome-content-container'>
          <p className='welcome-title'>Listening is</p>
          <p className='welcome-title'>everything</p>
          <p className='welcome-subtitle'>
            Millions of songs and playlists. Only need an account.
          </p>
          <button
            className='welcome-button'
            onClick={() => {
              navigate('/register')
            }}
          >
            GET SPOTIFY FREE
          </button>
        </div>

        <footer className='welcome-footer'>
          <p className='footer-text'>
            Developed by Juan Francisco Perez, Braian Espinoza & Nicolás De Maio
          </p>
          <p className='footer-text'>Spotify UI 2021</p>
        </footer>
      </div>
    </Fragment>
  )
}

export default WelcomeScreen
