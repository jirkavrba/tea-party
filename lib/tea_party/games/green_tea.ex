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

    @enforce_keys [:time_per_round, :points_per_game]

    defstruct [:time_per_round, :points_per_game]
  end

  defmodule Session do
    @moduledoc """
    Module representing a single green tea session (a lobby if you will)
    """
    @type t :: %__MODULE__{
        points: %{String.t() => non_neg_integer()},
        configuration: Configuration.t()
    }

    @enforce_keys [:points, :configuration]

    defstruct [:points, :configuration]

    @doc """
    Create a new green tea game session
    """
    @spec new(list(String.t()), Configuration.t()) :: t()
    def new(players, configuration) do
      # Each player starts with 0 points initially
      points =
        players
        |> Enum.map(fn player -> {player, 0} end)
        |> Map.new()

      %__MODULE__{
        points: points,
        configuration: configuration
      }
    end
  end

  @doc """
  Create a new green tea game session for the specified list of players and with the provided configuration
  """
  @spec new_session(list(String.t()), non_neg_integer(), non_neg_integer()) :: Session.t()
  def new_session(players, time_per_round \\ 10, points_per_game \\ 50) when time_per_round > 0 and points_per_game > 0 do
    configuration = %Configuration{
      time_per_round: time_per_round,
      points_per_game: points_per_game
    }

    Session.new(players, configuration)
  end
end
