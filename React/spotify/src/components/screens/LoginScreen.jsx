import { UserContext } from 'context/userContext'
import React, { useContext, useState } from 'react'
import { useNavigate } from 'react-router-dom'
import spotifyApi from '../../services/spotifyApi'
import InputErrorBar from '../atoms/InputErrorBar'
import SubmitFormButton from '../atoms/SubmitFormButton'
import LoginRegisterFooterWave from '../footers/LoginRegisterFooterWave'
import './LoginScreen.css'

const LoginScreen = () => {
  const [email, setEmail] = useState('')
  const [password, setPassword] = useState('')

  const [loginError, setLoginError] = useState(false)

  const navigate = useNavigate()

  const { setUser } = useContext(UserContext)

  const goToRegister = () => {
    navigate('/register')
  }

  const handleSubmit = (event) => {
    event.preventDefault()
    spotifyApi
      .loginUser(email, password)
      .then((user) => {
        setUser(user)
        navigate('/')
      })
      .catch(() => {
        setLoginError(true)
      })
  }

  const handleChange = (setValue) => {
    return (event) => setValue(event.target.value)
  }

  return (
    <>
      <div className='login-container'>
        <div className='login-labels'>
          <p className='title'>Spotify</p>
          <p className='subtitle'>
            Sign in and start playing your favorite songs
          </p>
        </div>

        <div className='login-card'>
          {loginError ? (
            <InputErrorBar text='Incorrect email or password' />
          ) : null}

          <form className='login-form' onSubmit={handleSubmit}>
            <input
              type='email'
              value={email}
              onChange={handleChange(setEmail)}
              className='form-input'
              placeholder='Email address'
            />
            <input
              type='password'
              value={password}
              onChange={handleChange(setPassword)}
              className='form-input'
              placeholder='Password'
            />
            <SubmitFormButton text={'Login'} />
          </form>

          <hr className='divider-line' />

          <button className='register-button' onClick={goToRegister}>
            Create new account
          </button>
        </div>
      </div>

      <LoginRegisterFooterWave />
    </>
  )
}

export default LoginScreen
