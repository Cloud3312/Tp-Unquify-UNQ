const shortFormattedDuration = (seconds) =>
  `${Math.floor(seconds / 60)}:${seconds % 60}`

const longFormattedDuration = (seconds) =>
  `${Math.floor(seconds / 60)} min ${seconds % 60} sec`

export default { shortFormattedDuration, longFormattedDuration }
