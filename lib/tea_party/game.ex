defmodule TeaParty.Game do
  @typedoc """
  Type of the game which sets the scoring mechanism used.
  There are currently only the following three modes supported:

  - Green tea - all that matters is speed, the fastest player receives 5 points,
    the second fastest receives 3 points, the third receives 1 points and others
    do not receive any points.

  - Yellow tea - the goal is to submit a space-separated list of words. The player
    with the largest number of valid words wins and receives 5 points. If there is a tie,
    player who submitted their word list receives the points.

  - Red tea - the goal is to submit the longest valid word possible. The player with
    the longest word receives 5 points. If there is a tie, player who submitted its
    word first receives the points.
  """
  @type mode :: :green_tea | :yellow_tea | :red_tea

  @type id :: String.t()

  @type t :: %__MODULE__{
    id: id(),
    mode: mode(),
    playing: boolean(),
    configuration: any(), # TODO: Implement game configuration
  }

  defstruct [:id, :mode, :configuration, :playing]
end
