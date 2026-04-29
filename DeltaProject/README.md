# DeltaProject

## Console command format

The command parser accepts object references in the form `typeN`, where `type` is the lowercase object type and `N` is the 1-based numeric ID.

Examples:
- `plumber1`
- `saboteur2`
- `pipe1`
- `pump3`
- `cistern1`
- `spring1`

## Notes

- `Prototype` is a thin console loop that delegates to `Parsers.CommandsParser`.
- Token parsing lives in `Parsers.ArgumentsParser`.
- Implemented command logic lives in the `Commands` package.
- Unimplemented commands print `NotImplementedYet`.

