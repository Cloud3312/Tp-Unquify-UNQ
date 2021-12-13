import './SubmitFormButton.css'
import React from 'react'

const SubmitFormButton = (props) => (
  <input className='submit-button' type='submit' value={props.text} />
)

export default SubmitFormButton
