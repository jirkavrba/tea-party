defmodule TeaParty.Games.GreenTea do
  @moduledoc """
  Green tea is a game mode, where all that matters is speed.

  Players simoultaneously receive a three-letter "syllable" and they have to write a word,
  that contains the provided letters as fast as possible, the first player to submit a valid word
  receives 5 points, the second receives 3 points and the third player receives 1 point, then all following submission are discarded.

  For each round there is a time limit after which a new round is started.
  """

  defmodule Configuration do
    @moduledoc """
    Configuration for the green tea game

    Available properties are:
    - `time_per_round` is the time for each round expressed in seconds
    - `points_per_round` is the total number of points that player has to exceed in order to win the whole game
    """

    @type t :: %__MODULE__{
      time_per_round: non_neg_integer(),
       points_per_game: non_neg_integer()
    }

    defstruct [time_per_round: 10, points_per_game: 50]

    @spec set_time_per_round(t(), non_neg_integer()) :: t()
    def set_time_per_round(%__MODULE__{} = configuration, time) when is_integer(time) and time > 0 do
      %__MODULE__{configuration | time_per_round: time}
    end

    @spec set_points_per_game(t(), non_neg_integer()) :: t()
    def set_points_per_game(%__MODULE__{} = configuration, points) when is_integer(points) and points > 0 do
      %__MODULE__{configuration | points_per_game: points}
    end
  end
end
