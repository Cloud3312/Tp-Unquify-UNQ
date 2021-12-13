import { UserContext } from 'context/userContext'
import React, { Fragment, useContext, useState } from 'react'
import { useNavigate } from 'react-router-dom'
import spotifyApi from '../../services/spotifyApi'
import InputErrorBar from '../atoms/InputErrorBar'
import SubmitFormButton from '../atoms/SubmitFormButton'
import LoginRegisterFooterWave from '../footers/LoginRegisterFooterWave'
import './RegisterScreen.css'

const RegisterScreen = () => {
  const navigate = useNavigate()

  const [name, setName] = useState('')
  const [email, setEmail] = useState('')
  const [password, setPassword] = useState('')
  const [profileImage, setProfileImage] = useState('')

  const [registerError, setRegisterError] = useState(false)

  const { setUser } = useContext(UserContext)

  const handleSubmit = (event) => {
    event.preventDefault()
    spotifyApi
      .registerUser(name, email, password, profileImage)
      .then((user) => {
        setUser(user)
        navigate('/')
      })
      .catch(() => {
        setRegisterError(true)
      })
  }

  const handleChange = (setValue) => {
    return (event) => setValue(event.target.value)
  }

  return (
    <Fragment>
      <div className='register-container'>
        <div className='login-labels'>
          <p className='title'>Register</p>
          <p className='subtitle'>
            Sign up to us platform and discover a lot of playlists
          </p>
        </div>

        <div className='login-card'>
          {registerError ? (
            <InputErrorBar text='Providen email is already used' />
          ) : null}

          <form className='login-form' onSubmit={handleSubmit}>
            <input
              className='form-input'
              type='text'
              value={name}
              onChange={handleChange(setName)}
              placeholder='Username'
              required
            />
            <input
              className='form-input'
              type='email'
              value={email}
              onChange={handleChange(setEmail)}
              placeholder='Email'
              required
            />
            <input
              className='form-input'
              type='password'
              value={password}
              onChange={handleChange(setPassword)}
              placeholder='Password'
              minLength={6}
              required
            />
            <input
              className='form-input'
              type='url'
              value={profileImage}
              onChange={handleChange(setProfileImage)}
              placeholder='URL Image'
              required
            />
            <SubmitFormButton text={'Register'} />
          </form>

          <hr className='divider-line' />

          <button
            className='login-link'
            onClick={() => {
              navigate('/login')
            }}
          >
            Do you have an account?
          </button>
        </div>
      </div>

      <LoginRegisterFooterWave />
    </Fragment>
  )
}

export default RegisterScreen
